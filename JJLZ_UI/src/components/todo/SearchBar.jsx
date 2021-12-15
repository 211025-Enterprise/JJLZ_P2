import React from 'react'
//import '../../App.css' 
function SearchBar(){

    return (<form action="https://google.com/search" method="get" align="center">
    <label htmlFor="header-search">
        <span className="visually-hidden" > Search Stock Name</span>
    </label>
    <input
        type="text"
        id="header-search"
        placeholder="Search blog posts"
        name="s" 
    />
    <button type="submit">Search</button>
    </form>)
    }
    export default SearchBar