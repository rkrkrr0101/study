module.exports = {
  development: {
    username: process.env.db_user,
    password: process.env.db_pw,
    database: process.env.db_db,
    host: process.env.db_host,
    // port: 3307,
    dialect: "mysql",
  },
  test: {
    username: process.env.db_user,
    password: process.env.db_pw,
    database: process.env.db_db,
    host: process.env.db_host,
    // port: 3307,
    dialect: "mysql",
  },
  production: {
    username: process.env.db_user,
    password: process.env.db_pw,
    database: process.env.db_db,
    host: process.env.db_host,
    // port: 3307,
    dialect: "mysql",
    logging: false,
  },
};
