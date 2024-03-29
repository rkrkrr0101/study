const express = require("express");
const multer = require("multer");
const path = require("path");
const fs = require("fs");

const { Good, Auction, User } = require("../models");
const { isLoggedIn, isNotLoggedIn } = require("./middlewares");

const router = express.Router();

router.use((req, res, next) => {
  res.locals.user = req.user;
  next();
});

router.get("/", async (req, res, next) => {
  try {
    const goods = await Good.findAll({ where: { SoldId: null } });
    res.render("main", {
      title: "NodeAuction",
      goods,
    });
  } catch (e) {
    console.error(e);
    next(e);
  }
});
router.get("/join", isNotLoggedIn, async (req, res, next) => {
  try {
    res.render("join", {
      title: "회원가입",
    });
  } catch (e) {
    console.error(e);
    next(e);
  }
});
try {
  fs.readdirSync("uploads");
} catch (e) {
  fs.mkdirSync("uploads");
}
const upload = multer({
  storage: multer.diskStorage({
    destination(req, file, cb) {
      cb(null, "uploads/");
    },
    filename(req, file, cb) {
      const ext = path.extname(file.originalname);
      cb(
        null,
        path.basename(file.originalname, ext) + new Date().valueOf() + ext
      );
    },
  }),
  limits: { fileSize: 5 * 1024 * 1024 },
});

router.get("/good", isLoggedIn, (req, res) => {
  res.render("good", { title: "상품등록" });
});

router.post(
  "/good",
  isLoggedIn,
  upload.single("img"),
  async (req, res, next) => {
    try {
      const { name, price } = req.body;
      await Good.create({
        OwnerId: req.user.id,
        name,
        img: req.file.filename,
        price,
      });
      res.redirect("/");
    } catch (e) {
      console.error(e);
      next(e);
    }
  }
);

router.get("/good/:id", isLoggedIn, async (req, res, next) => {
  try {
    const [good, auction] = await Promise.all([
      Good.findOne({
        where: { id: req.params.id },
        include: {
          model: User,
          as: "Owner",
        },
      }),
    ]);
    Auction.findAll({
      where: { GoodId: req.params.id },
      include: { model: User },
      order: [["bid", "ASC"]],
    });
    res.render("auction", {
      title: `${good.name}-NodeAuction`,
      good,
      auction,
    });
  } catch (e) {
    console.error(e);
    next(e);
  }
});

router.post("/good/:id/bid", isLoggedIn, async (req, res, next) => {
  try {
    const { bid, msg } = req.body;
    const good = await Good.findOne({
      where: { id: req.params.id },
      include: { model: Auction },
      order: [[{ model: Auction }, "bid", "DESC"]],
    });
    if (good.price >= bid) {
      return res.status(403).send("시작가격보다 높아야함");
    }
    if (new Date(good.createAt).valueOf() + 24 * 60 * 60 * 1000 < new Date()) {
      return res.status(403).send("경매종료됨");
    }
    if (good.Auctions[0] && good.Auctions[0].bid >= bid) {
      return res.status(403).send("이전입찰가보다 높아야함");
    }
    const result = await Auction.create({
      bid,
      msg,
      UserId: req.user.id,
      GoodId: req.params.id,
    });
    req.app.get("io").to(req.param.id).emit("bid", {
      bid: result.bid,
      msg: result.msg,
      nick: req.user.nick,
    });
    return res.send("ok");
  } catch (e) {
    console.error(e);
    return next(e);
  }
});

module.exports = router;
