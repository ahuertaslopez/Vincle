import React, { useState } from 'react';
import ItemList from './components/ItemList';
import ItemDetail from './components/ItemDetail';
import './App.css';

const App = () => {
	const [selectedItemId, setSelectedItemId] = useState(null);
	const [updateTrigger, setUpdateTrigger] = useState(false);

	const handleItemUpdated = () => {
		setUpdateTrigger(!updateTrigger);
	    setSelectedItemId(null);
	};
	  
	return (
		<div className="container">
			<div className="item-list">
				<ItemList onSelectItem={setSelectedItemId} onItemUpdated={handleItemUpdated} />
			</div>
			<ItemDetail itemId={selectedItemId} onItemUpdated={handleItemUpdated} />
		</div>
	);
};

export default App;