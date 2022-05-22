const jwt = require("jsonwebtoken");
const RateLimit = require("express-rate-limit");

exports.apiLimiter = RateLimit({
  windiwMs: 10 * 1000,
  max: 20,
  handler(req, res) {
    res.status(this.statusCode).json({
      code: this.statusCode,
      message: "1분에한번",
    });
  },
});
exports.deprecated = (req, res) => {
  res.status(410).json({
    code: 410,
    message: "새버전출시",
  });
};

exports.verifyToken = (req, res, next) => {
  try {
    req.decoded = jwt.verify(req.headers.authorization, process.env.JWT_SECRET);
    return next();
  } catch (e) {
    if (e.name === "TokenExpiredError") {
      return res.status(419).json({
        code: 419,
        message: "토큰만료",
      });
    }
    return res.status(401).json({
      code: 401,
      message: "유효하지않음",
    });
  }
};

exports.isLoggedIn = (req, res, next) => {
  if (req.isAuthenticated()) {
    next();
  } else {
    res.status(403).send("로그인필요");
  }
};
exports.isNotLoggedIn = (req, res, next) => {
  if (!req.isAuthenticated()) {
    next();
  } else {
    const message = encodeURIComponent("로그인중");
    res.redirect(`/?error=${message}`);
  }
};
