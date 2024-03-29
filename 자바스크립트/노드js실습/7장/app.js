const express = require("express");
const path = require("path");
const morgan = require("morgan");
const nunjucks = require("nunjucks");

const { sequelize } = require("./models");
const indexrouter = require("./routes");
const userrouter = require("./routes/users");
const commentrouter = require("./routes/comments");

const app = express();

app.set("port", process.env.PORT || 3001);
app.set("view engine", "html");
nunjucks.configure(__dirname + "/views", {
  express: app,
  watch: true,
});

sequelize
  .sync({ force: false })
  .then(() => {
    console.log("연결성공");
  })
  .catch((error) => {
    console.error(error);
  });

app.use(morgan("dev"));
app.use(express.static(path.join(__dirname, "public")));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use("/", indexrouter);
app.use("/users", userrouter);
app.use("/comments", commentrouter);

app.use((req, res, next) => {
  const error = new Error(`${req.method} ${req.url}  라우터없음`);
  error.status = 404;
  next(error);
});

app.use((err, req, res, next) => {
  res.locals.message = err.message;
  res.locals.error = process.env.NODE_ENV !== "production" ? err : {};
  res.status(err.status || 500);
  res.render("error");
});

app.listen(app.get("port"), () => {
  console.log(app.get("port"), "번포트에서 대기중");
});
