import React from 'react'
import {useParams} from "react-router-dom";

export const TableDetail = () => {
    return (
        <main>
            {useParams().tableId}
        </main>
    )
}