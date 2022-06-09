const Sequelize = require("sequelize");

module.exports = class Log extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        na_one: {
          type: Sequelize.STRING(45),
        },
        na_two: {
          type: Sequelize.STRING(45),
        },
        na_three: {
          type: Sequelize.STRING(45),
        },
        na_four: {
          type: Sequelize.STRING(45),
        },
        na_five: {
          type: Sequelize.STRING(45),
        },
        na_six: {
          type: Sequelize.STRING(45),
        },
        na_seven: {
          type: Sequelize.STRING(45),
        },
        na_eight: {
          type: Sequelize.STRING(45),
        },
        na_nine: {
          type: Sequelize.STRING(45),
        },
        na_ten: {
          type: Sequelize.STRING(45),
        },
        na_time: {
          type: Sequelize.DATE,
          allowNull: false,
          defaultValue: Sequelize.NOW,
        },
      },
      {
        sequelize,
        timestamps: false,
        underscoped: false,
        modelName: "Log",
        tableName: "namu_log",
        paranoid: false,
        charset: "utf8",
        collate: "utf8_general_ci",
      }
    );
  }
  static associate(db) {}
};
