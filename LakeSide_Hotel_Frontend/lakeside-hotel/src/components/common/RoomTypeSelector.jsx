import { useState, useEffect } from 'react';
import PropTypes from 'prop-types';  // Import PropTypes
import { getRoomTypes } from '../utils/ApiFunctions';

const RoomTypeSelector = ({ handleRoomInputChange, newRoom }) => {
    const [roomTypes, setRoomTypes] = useState([]);
    const [showRoomTypeInput, setShowRoomTypeInput] = useState(false);
    const [newRoomType, setNewRoomType] = useState("");

    useEffect(() => {
        getRoomTypes().then((data) => setRoomTypes(data));
    }, []);

    const handleNewRoomInputChange = (e) => {
        setNewRoomType(e.target.value);
    };

    const handleAddNewRoomType = () => {
        if (newRoomType !== "") {
            setRoomTypes([...roomTypes, newRoomType]);
            setShowRoomTypeInput(false);
            setNewRoomType("");
        }
    };

    return (
        <>
            {roomTypes.length > 0 && (
                <div>
                    <select
                        name="roomType"
                        id="roomType"
                        value={newRoom?.roomType || ""}
                        onChange={(e) => {
                            if (e.target.value === "Add New") {
                                setShowRoomTypeInput(true);
                            } else {
                                handleRoomInputChange(e);
                            }
                        }}
                    >
                    <option value={""}>select a room type</option>
                    <option value={"Add New"}>Add New</option>

                        {roomTypes.map((type, index) => (
                            <option key={index} value={type}>
                                {type}
                            </option>
                        ))}
                         
                    </select>
                    {showRoomTypeInput && (
                <div className='input-group'>
                    <input
                    className='form-control'
                        type="text"
                        placeholder="Enter a new room type"
                        value={newRoomType}
                        onChange={handleNewRoomInputChange}
                    />
                    <button className='btn btn-hotel 'type='button' onClick={handleAddNewRoomType}>Add</button>
                </div>
            )}
                </div>
            )}

            
        </>
    );
};

// PropTypes validation
RoomTypeSelector.propTypes = {
    handleRoomInputChange: PropTypes.func.isRequired,
    newRoom: PropTypes.shape({
        roomType: PropTypes.string
    })
};

// Default Props (optional)
RoomTypeSelector.defaultProps = {
    newRoom: {
        roomType: ""
    }
};

export default RoomTypeSelector;
