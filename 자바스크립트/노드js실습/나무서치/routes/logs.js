const express = require("express");
const Namu_Data = require("../models/data");
const router = express.Router();

router.route("/hour").get(async (req, res, next) => {
  try {
    const Namu_Datas = await Namu_Data.findAll({
      where: {
        nd_kind: "hour",
      },
    });

    res.json(Namu_Datas);
  } catch (e) {
    console.error(e);
    next(e);
  }
});
router.route("/day").get(async (req, res, next) => {
  try {
    const Namu_Datas = await Namu_Data.findAll({
      where: {
        nd_kind: "day",
      },
    });

    res.json(Namu_Datas);
  } catch (e) {
    console.error(e);
    next(e);
  }
});
router.route("/week").get(async (req, res, next) => {
  try {
    const Namu_Datas = await Namu_Data.findAll({
      where: {
        nd_kind: "week",
      },
    });

    res.json(Namu_Datas);
  } catch (e) {
    console.error(e);
    next(e);
  }
});
router.route("/month").get(async (req, res, next) => {
  try {
    const Namu_Datas = await Namu_Data.findAll({
      where: {
        nd_kind: "month",
      },
    });

    res.json(Namu_Datas);
  } catch (e) {
    console.error(e);
    next(e);
  }
});

module.exports = router;
