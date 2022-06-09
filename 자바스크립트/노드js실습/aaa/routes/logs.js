const express = require("express");
const Log = require("../models/log");
const router = express.Router();

router.route("/").get(async (req, res, next) => {
  try {
    const logs = await Log.findAll({
      limit: 2,
      order: [["na_time"]],
    });

    res.json(logs);
  } catch (e) {
    console.error(e);
    next(e);
  }
});
router.route("/today").get(async (req, res, next) => {
  try {
    const logs = await Log.findAll({
      limit: 2,
      order: [["na_time", "DESC"]],
    });
    res.json(logs);
  } catch (e) {
    console.error(e);
    next(e);
  }
});

module.exports = router;
