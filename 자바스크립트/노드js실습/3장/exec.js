const exec = require('child_process').exec;

var process = exec('copy readme2.txt readme24.txt');

process.stdout.on('data', function(data) {
  console.log(data.toString());
  console.log('성공');
}); // 실행 결과

process.stderr.on('data', function(data) {
  console.error(data.toString());
  console.log('실패');
}); // 실행 에러
