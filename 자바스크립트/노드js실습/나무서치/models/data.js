const Sequelize = require("sequelize");

module.exports = class namuData extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        nd_one: {
          type: Sequelize.STRING(45),
        },
        nd_two: {
          type: Sequelize.STRING(45),
        },
        nd_three: {
          type: Sequelize.STRING(45),
        },
        nd_four: {
          type: Sequelize.STRING(45),
        },
        nd_five: {
          type: Sequelize.STRING(45),
        },
        nd_six: {
          type: Sequelize.STRING(45),
        },
        nd_seven: {
          type: Sequelize.STRING(45),
        },
        nd_eight: {
          type: Sequelize.STRING(45),
        },
        nd_nine: {
          type: Sequelize.STRING(45),
        },
        nd_ten: {
          type: Sequelize.STRING(45),
        },
        nd_time: {
          type: Sequelize.DATE,
          allowNull: false,
          defaultValue: Sequelize.NOW,
        },
      },
      {
        sequelize,
        timestamps: false,
        underscoped: false,
        modelName: "namuData",
        tableName: "namu_data",
        paranoid: false,
        charset: "utf8",
        collate: "utf8_general_ci",
      }
    );
  }
  static associate(db) {}
};
