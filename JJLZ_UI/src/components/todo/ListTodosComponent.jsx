import React, {Component} from 'react'

class ListTodosComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            todos : 
            [
             {id: 1, description : 'Amazon',price: '$5000' , done:false, targetDate: new Date()},
             {id: 2, description : 'Home Depo',price: '$5000' , done:false, targetDate: new Date()},
             {id: 3, description : 'Revature',price: '$5000' , done:false, targetDate: new Date()}
            ]
        }
    }

    render() {
        return (
            <div>
                 <h1>Watch List</h1>
                 <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Stock Name</th>
                                <th>Price</th>
                                <th>Target Date</th>
                                <th>IsCompleted?</th>
                            </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.todos.map (
                                todo =>
                                    <tr key={todo.id}>
                                        <td>{todo.description}</td>
                                        <td>{todo.price}</td>
                                        <td>{todo.done.toString()}</td>
                                        <td>{todo.targetDate.toString()}</td>
                                    </tr>
                            )
                            }
                        </tbody>
                    </table>
                 </div>
            </div>
        )
    }
}

export default ListTodosComponent