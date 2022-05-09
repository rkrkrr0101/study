const fs=require('fs')

const ws=fs.createWriteStream('./writeme2.txt')
ws.on('finish',()=>{
	console.log('파일쓰기완료')
})
ws.write('abcd\n')
ws.write('ccc')
ws.end()