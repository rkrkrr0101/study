const path= require('path')

const str=__filename
const ss=path.relative('C:\\Users\\User\\Documents\\github\\study\\자바스크립트\\노드js실습\\3장','C:\\Users\\User\\Documents\\github\\study\\자바스크립트\\노드js실습\\2장')

console.log(path.sep)
console.log(path.delimiter)
console.log(path.dirname(str))
console.log(path.extname(str))
console.log(path.basename(str))
console.log(path.basename(str,path.extname(str)))
console.log(path.parse(str))
console.log(path.normalize('C:\\Users\\User\\Documents\\github\\study\\자바스크립트\\노드js실습\\3장') )
console.log(path.isAbsolute('c:\\'))
console.log(path.relative('C:\\Users\\User\\Documents\\github\\study\\자바스크립트\\노드js실습\\3장','C:\\Users\\User\\Documents\\github\\study\\자바스크립트\\노드js실습\\2장'))
console.log(path.join('C:\\Users\\User\\Documents\\github\\study\\자바스크립트\\노드js실습\\3장',ss))



