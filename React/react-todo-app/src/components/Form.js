import React from 'react'

export default function Form({value, setValue, handleSubmit}) {
    const handleChange = (e) => {
        setValue(e.target.value);
    }

    return (
        <form style={{display: 'flex'}} onSubmit={handleSubmit} className='flext pt-2'>
            <input type="text" name="value" placeholder="해야 할 일을 입력하세요" 
            value={value}
            className="w-full px-3 py-2 mr-4 text-gray-500 rounded shadow"
            onChange={handleChange}/>
            <input className='p-2 text-blue-400 border-2 border-blue-400 rounded hover:text-white hover:bg-blue-200' type="submit" value="입력" />
        </form>
    )
}
