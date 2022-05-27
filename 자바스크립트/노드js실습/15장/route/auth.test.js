const request = require("supertest");
const { sequelize } = require("../models");
const app = require("../app");

beforeAll(async () => {
  await sequelize.sync();
});

describe("Post /join", () => {
  test("가입", (done) => {
    request(app)
      .post("/auth/join")
      .send({
        email: "ze@na.com",
        nick: "abcd",
        password: "1234",
      })
      .expect("Location", "/")
      .expect(302, done);
  });
});
describe("Post /join", () => {
  const agent = request.agent(app);

  beforeEach((done) => {
    agent
      .post("/auth/login")
      .send({
        email: "ze@na.com",
        password: "1234",
      })
      .end(done);
  });
  test("로그인중일때", (done) => {
    const message = encodeURIComponent("로그인중");
    agent
      .post("/auth/join")
      .send({
        email: "ze@na.com",
        nick: "abcd",
        password: "1234",
      })
      .expect("Location", `/?error=${message}`)
      .expect(302, done);
  });
});

// describe("Post /login", () => {
//   test("로그인 수행", (done) => {
//     request(app)
//       .post("/auth/login")
//       .send({
//         email: "ze@na.com",
//         password: "1234",
//       })
//       .expect("location", "/")
//       .expect(302, done);
//   });
// });

afterAll(async () => {
  await sequelize.sync({ force: true });
});
