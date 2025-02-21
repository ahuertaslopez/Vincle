import React, { useEffect, useState } from 'react';
import { getItems, deleteItem } from '../services/api';

const ItemList = ({ onSelectItem, onItemUpdated }) => {
	const [items, setItems] = useState([]);
	
	const fetchItems = () => {
	    getItems().then(response => {
			  console.log('Items fetched:', response.data);
			  setItems(response.data)
	    }).catch(error => console.error('Error fetching items:', error));
	};
	
	useEffect(() => { 
		fetchItems();
	}, []);

	useEffect(() => {
		fetchItems();
	}, [onItemUpdated]);

	const handleDelete = (id) => {
		deleteItem(id).then(() => {
			onItemUpdated();
			onSelectItem(null);
		});
	};
  
	return (
			<div>
				<h1>Items</h1>
				<ul>
					{items.map(item => (
					<li key={item.id}>
						<span onClick={() => onSelectItem(item.id)} style={{ cursor: 'pointer' }}>
							{item.id}: {item.name} ({item.type}). En estado {item.status}
						</span>
						<button className="delete" onClick={() => handleDelete(item.id)}>Delete</button>
					</li>
					))}
				</ul>
			</div>
	);
};

export default ItemList;