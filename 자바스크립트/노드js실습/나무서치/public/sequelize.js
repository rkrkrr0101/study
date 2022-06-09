async function getNamuData(kind, selector) {
  try {
    const res = await axios.get(`/logs/${kind}`);
    const NamuDatas = res.data;
    //console.log(selector);
    //user-list tbody
    const tbody = document.querySelector(selector);
    tbody.innerHTML = "";
    NamuDatas.map(function (NamuData) {
      const row = document.createElement("tr");
      // 로우 셀 추가
      let td = document.createElement("td");
      td.textContent = NamuData.nd_one;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = NamuData.nd_two;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = NamuData.nd_three;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = NamuData.nd_four;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = NamuData.nd_five;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = NamuData.nd_six;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = NamuData.nd_seven;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = NamuData.nd_eight;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = NamuData.nd_nine;
      row.appendChild(td);
      td = document.createElement("td");
      td.textContent = NamuData.nd_ten;
      row.appendChild(td);
      tbody.appendChild(row);
    });
  } catch (err) {
    console.error(err);
  }
}

// 댓글 로딩

// 사용자 등록 시
getNamuData("hour", "#hour tbody");
getNamuData("day", "#day tbody");
getNamuData("week", "#week tbody");
getNamuData("month", "#month tbody");
