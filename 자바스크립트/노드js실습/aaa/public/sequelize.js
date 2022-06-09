async function getLog() {
  try {
    const res = await axios.get("/logs");
    const logs = res.data;
    //console.log(logs);
    const tbody = document.querySelector("#user-list tbody");
    tbody.innerHTML = "";
    logs.map(function (log) {
      const row = document.createElement("tr");
      // 로우 셀 추가
      let td = document.createElement("td");
      td.textContent = log.na_one;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_two;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_three;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_four;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_five;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_six;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_seven;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_eight;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_nine;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_ten;
      row.appendChild(td);
      tbody.appendChild(row);
    });
  } catch (err) {
    console.error(err);
  }
}
async function getLoga() {
  try {
    const res = await axios.get("/logs/today");
    const logs = res.data;
    //console.log(logs);
    const tbody = document.querySelector("#user-listds tbody");
    tbody.innerHTML = "";
    logs.map(function (log) {
      const row = document.createElement("tr");
      // 로우 셀 추가
      let td = document.createElement("td");
      td.textContent = log.na_one;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_two;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_three;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_four;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_five;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_six;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_seven;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_eight;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_nine;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = log.na_ten;
      row.appendChild(td);
      tbody.appendChild(row);
    });
  } catch (err) {
    console.error(err);
  }
}
// 댓글 로딩

// 사용자 등록 시
getLog();
getLoga();
