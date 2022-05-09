const url=require('url')

const {URL}=url
const myurl=new URL('http://www.gilbut.co.kr/book/bookList.aspx?sercate1=001001000#anchor');
console.log(myurl)
console.log(url.format(myurl))
const parseurl=url.parse('http://www.gilbut.co.kr/book/bookList.aspx?sercate1=001001000#anchor');
console.log(parseurl)
console.log(url.format(parseurl))