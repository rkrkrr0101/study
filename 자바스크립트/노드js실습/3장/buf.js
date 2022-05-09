const buffer=Buffer.from('저를 버퍼로 바꿔보세요')
console.log(buffer)
console.log(buffer.length)
console.log(buffer.toString())

console.log(Buffer.concat([Buffer.from('저를'),Buffer.from('바꿔보세요'),Buffer.from('버퍼로')]).toString())
console.log(Buffer.alloc(5))