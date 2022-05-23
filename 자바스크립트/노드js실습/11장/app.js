const express = require("express");
const path = require("path");
const morgan = require("morgan");
const cookieParser = require("cookie-parser");
const session = require("express-session");
const dotenv = require("dotenv");
const multer = require("multer");
const nunjucks = require("nunjucks");
const { sequelize } = require("./models");
const passport = require("passport");

dotenv.config();
const pageRouter = require("./route/page.js");
const authRouter = require("./route/auth");
const postRouter = require("./route/post");
const userRouter = require("./route/user");
const passportConfig = require("./passport");

const app = express();
passportConfig();
app.set("port", process.env.PORT || 3000);
app.set("view engine", "html");

nunjucks.configure("views", {
  express: app,
  watch: true,
});
sequelize
  .sync({ force: false })
  .then(() => {
    console.log("성공");
  })
  .catch((e) => {
    console.error(e);
  });

app.use(morgan("dev"));
app.use("/", express.static(path.join(__dirname, "public")));
app.use("/img", express.static(path.join(__dirname, "uploads")));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser(process.env.COOKIE_SECRET));
app.use(
  session({
    resave: false,
    saveUninitialized: false,
    secret: process.env.COOKIE_SECRET,
    cookie: {
      httpOnly: true,
      secure: false,
    },
  })
);
app.use(passport.initialize());
app.use(passport.session());

app.use("/", pageRouter);
app.use("/auth", authRouter);
app.use("/post", postRouter);
app.use("/user", userRouter);
app.use((req, res, next) => {
  const error = new Error(`${req.method}${req.url}라우터없음 `);
  error.status = 404;
  next(error);
});

app.use((err, req, res, next) => {
  res.locals.message = err.message;
  res.locals.error = process.env.NODE_ENV !== "production" ? err : {};
  res.status(err.status || 500);
  res.render("error");
});

module.exports = app;

// app.listen(app.get("port"), () => {
//   console.log(app.get("port"), "번에서 대기");
// });
