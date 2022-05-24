const SocketIO = require("socket.io");

module.exports = (server) => {
  const io = new SocketIO(server, { path: "/socket.io" });

  io.on("connection", (socket) => {
    const req = socket.request;
    const ip = req.headers["x-forwarded-for"] || req.connection.remoteAddress;
    console.log("새클라접속", ip, socket.id, req.ip);
    socket.on("reply", (data) => {
      console.log(data);
    });
    socket.on("error", (error) => {
      console.error(error);
    });
    socket.on("disconnect", () => {
      console.log("클라접속해제", ip, socket.id);
      clearInterval(socket.interval);
    });
    socket.interval = setInterval(() => {
      socket.emit("news", "hellosocketio");
    }, 3000);
  });
};
