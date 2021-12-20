import React from "react";
function SearchBar()  {

    return (
    

      <form action="/stock" method="get" align="center">
        <label htmlFor="header-search">
          <span className="visually-hidden"> Search Stock Name</span>
        </label>
        <input
          type="text"
          id="header-search"
          placeholder="Enter Stock Name"
          name="s"
        />
        <button type="submit">Search</button>
      </form>   
    );
}
export default SearchBar;
