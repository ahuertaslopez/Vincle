import React, { useEffect, useState } from 'react';
import { getItem, updateItem } from '../services/api';

const ItemDetail = ({ itemId, onItemUpdated }) => {
  const [item, setItem] = useState(null);

  useEffect(() => {
    if (itemId) {
      getItem(itemId).then(response => setItem(response.data));
    }
  }, [itemId]);

  const handleUpdate = () => {
    const updatedItem = { 
    		...item, 
    		status: 'DELETED',
    		timestamp: new Date().toISOString()};
    updateItem(itemId, updatedItem).then(() => {
    		onItemUpdated();
    		setItem(null);
    });
  };
	  
  if (!item) return <div className="detail"> Selecciona un item para ver los detalles </div>;

  return (
	<div className="detail">
		<h2>{item.name}</h2>
		<p>Tipo: {item.type}</p>
		<p>¿Precisa nevera?: {item.requiresRefrigeration ? 'Sí' : 'No'}</p>
		<p>Capacidad: {item.capacity}</p>
		<p>Envase: {item.container}</p>
		<p>Nombre cliente: {item.clientName}</p>
		<p>Fecha operación: {item.timestamp}</p>
		<p>Estado: {item.status}</p>
		{item.status !== 'DELETED' && <button className="delete" onClick={handleUpdate}>Desclasificar</button>}
	</div>
  );
};

export default ItemDetail;