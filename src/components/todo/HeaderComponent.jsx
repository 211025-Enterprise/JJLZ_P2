import React, {Component} from 'react'
import {Link} from 'react-router-dom'
import AuthenticationService from './AuthenticationService.js'


class HeaderComponent extends Component {
    render() {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        //console.log(isUserLoggedIn);

        return (
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-info">
                    <div><a href="http://www.GOOGLE.com" className="navbar-brand">JJLZ</a></div>
                    <ul className="navbar-nav">
                        {isUserLoggedIn && <li><Link className="nav-link" to="/welcome/JJLZ">Home</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/todos">Watch List</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/userDetail">UserDetail</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/stock">Stock_Symbol</Link></li>}
                    </ul>
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                    {<li><Link className="nav-link" to="/register">Register</Link></li>}
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>Logout</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }
}
export default HeaderComponent