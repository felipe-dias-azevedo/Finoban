import React from 'react';
import { useDrop } from 'react-dnd';

function ChartHolder({children, id, giant, moveCard}) {
    
    const [, dropRef] = useDrop({
        accept: "CHART",
        drop: (item) => {
            moveCard(item.id-1, id-1);
        },
        collect: (monitor) => ({
            isOver: !!monitor.isOver(),
        }),
    });

    return (
        <div
            ref={dropRef}
            className={`chart ${giant ? "giant" : "tiny"}-chart`}
        >
                {children}
        </div>
    );
}

export default ChartHolder;