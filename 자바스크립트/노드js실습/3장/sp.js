const url = require('url');

const { URL } = url;
const myurl = new URL('http://www.gilbut.co.kr/?page=3&limit=10&category=nodejs&category=javascript');
console.log(myurl.searchParams)
console.log(myurl.searchParams.getAll('category'))
console.log(myurl.searchParams.get('limit'))
console.log(myurl.searchParams.has('page'))
console.log(myurl.searchParams.keys())
console.log(myurl.searchParams.values())
console.log(myurl.searchParams.append('filter','es3'))
console.log(myurl.searchParams.append('filter','es5'))
console.log(myurl.searchParams.getAll('filter'))
myurl.searchParams.set('filter','es6')
console.log(myurl.searchParams.getAll('filter'))
myurl.searchParams.delete('filter')
console.log(myurl.searchParams.getAll('filter'))
console.log(myurl.searchParams.toString())
myurl.search=myurl.searchParams.toString()