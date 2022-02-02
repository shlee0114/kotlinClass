import React, { useEffect, useState } from 'react'
import {useParams} from "react-router-dom";
import axios from 'axios'

const TableInfo = ({writer, title, content}) => {
    return(
        <div>
            writer : {writer}<br/>
            title : {title}<br/>
            content : {content}<br/>
        </div>
    )
}

export const TableDetail = () => {
    const [table, setTableInfo] = useState([])
    const params = useParams();

    useEffect(() => {
        axios(
            {
                url : `http://localhost:5000/table/${params.tableId}`,
                method : 'GET'
            }
        ).then( response => {
            console.log(response)
            setTableInfo(response.data.data)
        })
    }, [])

    return (
        <main>
        {<TableInfo writer={table.writer} title={table.title} content={table.content}/>}
        </main>
    )
}