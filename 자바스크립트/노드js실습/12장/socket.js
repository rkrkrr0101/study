const SocketIO = require("socket.io");
const axios = require("axios");

module.exports = (server, app, sessionMiddleware) => {
  const io = new SocketIO(server, { path: "/socket.io" });
  app.set("io", io);
  const room = io.of("/room");
  const chat = io.of("/chat");

  io.use((socket, next) => {
    sessionMiddleware(socket.request, socket.request.res, next);
  });

  room.on("connection", (socket) => {
    console.log("room접속");
    socket.on("disconnect", () => {
      console.log("room접속해제");
    });
  });

  chat.on("connection", (socket) => {
    console.log("chat접속");
    const req = socket.request;
    const {
      headers: { referer },
    } = req;
    const roomId = referer
      .split("/")
      [referer.split("/").length - 1].replace(/\?.+/, "");
    socket.join(roomId);
    socket.to(roomId).emit("join", {
      user: "system",
      chat: `${req.session.color}님이 입장`,
    });

    socket.on("disconnect", () => {
      console.log("chat접속해제");
      socket.leave(roomId);
      const currentRoom = socket.adapter.rooms[roomId];
      const userCount = currentRoom ? currentRoom.length : 0;
      if (userCount === 0) {
        axios
          .delete(`http://localhost:3000/room/${roomId}`)
          .then(() => {
            console.log("방제거요청성공");
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        socket.to(roomId).emit("exit", {
          user: "system",
          chat: `${req.session.color}님이 퇴장`,
        });
      }
    });
  });
};
