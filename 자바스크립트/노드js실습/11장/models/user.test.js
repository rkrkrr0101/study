const Sequelize = require("sequelize");
const { belongsToMany } = require("./user");
const User = require("./user");
const config = require("../config/config")["test"];
const sequelize = new Sequelize(
  config.database,
  config.username,
  config.password,
  config
);

describe("user모델", () => {
  test("static init", () => {
    expect(User.init(sequelize)).toBe(User);
  });
  test("static asso", () => {
    const db = {
      User: {
        hasMany: jest.fn(),
        belongsToMany: jest.fn(),
      },
      Post: {},
    };
    User.associate(db);
    expect(db.User.hasMany).toHaveBeenCalledWith(db.Post);
    expect(db.User.belongsToMany).toHaveBeenCalledTimes(2);
  });
});
