import React from 'react'
import { DragDropContext, Draggable, Droppable } from 'react-beautiful-dnd';
import List from "./List";

const Lists = React.memo(({todoData, setTodoData}) => {
    const handleEnd = (result) => {
        const { source, destination } = result;
    
        // 목적지가 없는 경우 (드래그했지만 같은 자리에 놓거나, 유효하지 않은 위치에 놓은 경우)
        if (!destination) {
            return;
        }
    
        // 소스와 목적지의 인덱스가 같은 경우, 즉 위치가 변하지 않은 경우
        if (
            source.droppableId === destination.droppableId &&
            source.index === destination.index
        ) {
            return;
        }
    
        // 배열을 재배열하는 로직
        const newTodoData = reorder(todoData, source.index, destination.index);
        setTodoData(newTodoData);
    };
    
    // 배열을 재배열하는 함수
    const reorder = (list, startIndex, endIndex) => {
        const result = Array.from(list);
        const [removed] = result.splice(startIndex, 1);
        result.splice(endIndex, 0, removed);
    
        return result;
    };
  return (
    <div>
    <DragDropContext onDragEnd={handleEnd}>
        <Droppable droppableId='todod'>
            {(provided) => (
                <div {...provided.droppableProps} ref={provided.innerRef}>
                    {todoData.map((data,index) => (          
                    <Draggable
                        key={data.id}
                        draggableId={data.id.toString()}
                        index={index}
                    >
                        {(provided, snapshot) => (
                            <List
                                key={data.id}
                                id={data.id}
                                title={data.title}
                                completed={data.completed}
                                todoData={todoData}
                                setTodoData={setTodoData}
                                provided={provided}
                                snapshot={snapshot}
                            />
                        )}
                        </Draggable>
                    ))}
                    {provided.placeholder}
                </div>
            )}
        </Droppable>
    </DragDropContext>
</div>
  )
})

export default Lists;
