const { isLoggedIn, isNotLoggedIn } = require("./middlewares");

describe("IsLoggedIn", () => {
  const res = {
    status: jest.fn(() => res),
    send: jest.fn(),
  };
  const next = jest.fn();
  test("로그인시", () => {
    const req = { isAuthenticated: jest.fn(() => true) };
    isLoggedIn(req, res, next);
    expect(next).toBeCalledTimes(1);
  });
  test("비로그인시에러", () => {
    const req = { isAuthenticated: jest.fn(() => false) };
    isLoggedIn(req, res, next);
    expect(res.status).toBeCalledWith(403);
    expect(res.send).toBeCalledWith("로그인필요");
  });
});
describe("IsNotLoggedIn", () => {
  const res = {
    redirect: jest.fn(),
  };
  const next = jest.fn();
  test("비로그인시", () => {
    const req = { isAuthenticated: jest.fn(() => false) };
    isNotLoggedIn(req, res, next);
    expect(next).toBeCalledTimes(1);
  });
  test("로그인시에러", () => {
    const req = { isAuthenticated: jest.fn(() => true) };
    isNotLoggedIn(req, res, next);
    const message = encodeURIComponent("로그인중");
    expect(res.redirect).toBeCalledWith(`/?error=${message}`);
  });
});
