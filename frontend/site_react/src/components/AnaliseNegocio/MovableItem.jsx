import { useDrag } from "react-dnd";
import { Link } from "react-router-dom";

function MovableItem({children, id, name, idGraph}) {

    const [, drag] = useDrag({
        item: {
            type: 'CHART',
            id,
            name
        },
        collect: monitor => ({
            isDragging: monitor.isDragging()
        })
    });

    return (
        <div
            ref={drag}
            className="movable-item chart-drag"
            id={id}
        >
            <Link to={`/analise/dashboard/${idGraph}`}>
                <h3>{name}</h3>
            </Link>
            <div>
                {children}
            </div>
        </div>
    )
}

export default MovableItem;