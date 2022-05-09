const url = require('url');
const { URL } = url;
const qs=require('querystring')

const ps = url.parse('http://www.gilbut.co.kr/?page=3&limit=10&category=nodejs&category=javascript');
const query=qs.parse(ps.query)
console.log(query)
console.log(qs.stringify(query))

