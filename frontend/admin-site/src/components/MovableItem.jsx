import { useDrag } from "react-dnd";

const MovableItem = ({children, id, name}) => {

    const [{ isDragging }, drag] = useDrag({
        item: {
            type: 'CHART',
            id,
            name
        },
        collect: monitor => ({
            isDragging: monitor.isDragging()
        })
    });
    
    const opacity = isDragging ? "0.3" : "1";
    const cursor = isDragging ? "copy" : "auto";

    return (
        <div
            ref={drag}
            className="movable-item chart-drag"
            id={id}
            style={{ opacity, cursor }}
        >
            <h2>{name}</h2>
            <div>
                {children}
            </div>
        </div>
    )
}

export default MovableItem;