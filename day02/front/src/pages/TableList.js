import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useNavigate } from "react-router-dom";

const Table = ({writer, title, id}) => {
    const navigate = useNavigate();
  
    return (
        <tr onClick={() => {navigate(`/${id}`)}}>
            <td>
                {writer}
            </td>
            <td>
                {title}
            </td>
        </tr>
    )
}

export const TableList = () => {
    const [table, setTableList] = useState([])
    useEffect(() => {
        axios(
            {
                url : 'http://localhost:5000/table',
                method : 'GET'
            }
        ).then( response => {
            console.log(response)
            setTableList(response.data.data)
        })
    }, [])
    return (
            <table>
                <thead>
                    <tr>
                        <td>
                            작성자
                        </td>
                        <td>
                            제목
                        </td>
                    </tr>
                </thead>
                <tbody>
                    {table.map(rowData => {
                        return (
                            <Table writer={rowData.writer} title={rowData.title} id={rowData.id}/>
                        )
                    })}
                </tbody>
            </table>
    )
}