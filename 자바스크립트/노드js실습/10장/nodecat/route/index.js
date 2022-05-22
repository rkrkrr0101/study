const express = require("express");
const axios = require("axios");
const router = express.Router();

const url = "http://localhost:3002/v2/";
axios.defaults.headers.origin = "http://localhost:3003";
const request = async (req, api) => {
  try {
    if (!req.session.jwt) {
      const tokenResult = await axios.post(`${url}/token`, {
        clientSecret: process.env.CLIENT_SECRET,
      });
      req.session.jwt = tokenResult.data.token;
    }
    return await axios.get(`${url}${api}`, {
      headers: { authorization: req.session.jwt },
    });
  } catch (e) {
    console.error(e);
    if (e.response.status === 419) {
      delete req.session.jwt;
      return request(req, api);
    }
    return e.response;
  }
};

router.get("/", (req, res) => {
  res.render("main", { key: process.env.CLIENT_SECRET });
});

router.get("/mypost", async (req, res, next) => {
  try {
    const result = await request(req, "posts/my");
    res.json(result.data);
  } catch (e) {
    console.error(e);
    next(e);
  }
});

router.get("/search/:hashtag", async (req, res, next) => {
  try {
    const result = await request(
      req,
      `/posts/hashtag/${encodeURIComponent(req.params.hashtag)}`
    );
    res.json(result.data);
  } catch (e) {
    console.error(e);
    next(e);
  }
});

router.get("/test", async (req, res, next) => {
  try {
    if (!req.session.jwt) {
      const tokenResult = await axios.post("http://localhost:3002/v2/token", {
        clientSecret: process.env.CLIENT_SECRET,
      });
      if (tokenResult.data && tokenResult.data.code === 200) {
        req.session.jwt = tokenResult.data.token;
      } else {
        return res.json(tokenResult.data);
      }
    }

    const result = await axios.get("http://localhost:3002/v2/test", {
      headers: { authorization: req.session.jwt },
    });
    return res.json(result.data);
  } catch (e) {
    console.error(e);
    if (e.response.status === 419) {
      return res.json(e.response.data);
    }
    return next(e);
  }
});

module.exports = router;
