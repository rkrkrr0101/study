const express = require("express");
const { isLoggedIn, isNotLoggedIn } = require("./middlewares");

const router = express.Router();

router.use((req, res, next) => {
  res.locals.user = req.user;
  res.locals.followerCount = 0;
  res.locals.followingCount = 0;
  res.locals.followerIdList = [];
  next();
});

router.get("/profile", isLoggedIn, (req, res) => {
  res.render("profile", { title: "내정보-nodebird" });
});
router.get("/join", isNotLoggedIn, (req, res) => {
  res.render("join", { title: "회원가입-nodebird" });
});
router.get("/", (req, res) => {
  const twits = [];
  res.render("main", { title: "nodebird", twits });
});

module.exports = router;
