import { useState } from "react";
import { addRoom } from "../utils/ApiFunctions";

const AddRoom = () => {
  const [newRoom, setNewRoom] = useState({
    photo: null,
    roomType: "",
    roomPrice: "",
  });

  const [imagePreview, setImagePreview] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleRoomInputChange = (e) => {
    const name = e.target.name;
    let value = e.target.value;

    if (name === "roomPrice") {
      if (!isNaN(value)) {
        value = parseInt(value);  // Fixed reassignment of value
      } else {
        value = "";  // Reset if not a number
      }
    }
    setNewRoom({ ...newRoom, [name]: value });
  };

  const handleImageChange = (e) => {
    const selectedImage = e.target.files[0];
    setNewRoom({ ...newRoom, photo: selectedImage });
    setImagePreview(URL.createObjectURL(selectedImage));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const success = await addRoom(
        newRoom.photo,
        newRoom.roomType,
        newRoom.roomPrice
      );
      if (success) {
        setSuccessMessage("Room Added Successfully");
        setNewRoom({ photo: null, roomType: "", roomPrice: "" });
        setImagePreview("");
        setErrorMessage("");
      } else {
        setErrorMessage("Error Adding Room");
      }
    } catch (error) {
      setErrorMessage(error.message);
    }
  };

  return (
    <>
      <section className="container mt-5 mb-5">
        <div className="row d-flex justify-content-center">
          <div className="col-md-8 col-lg-6">
            <h2 className="mt-5 mb-2">Add a New Room</h2>

            {/* Display Success or Error Messages */}
            {successMessage && (
              <div className="alert alert-success">{successMessage}</div>
            )}
            {errorMessage && (
              <div className="alert alert-danger">{errorMessage}</div>
            )}

            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <label htmlFor="roomType" className="form-label">
                  Room Type
                </label>
                <select
                  className="form-control"
                  id="roomType"
                  name="roomType"
                  value={newRoom.roomType}
                  onChange={handleRoomInputChange}
                >
                  <option value="">Select Room Type</option>
                  <option value="Single">Single</option>
                  <option value="Double">Double</option>
                  <option value="Suite">Suite</option>
                </select>
              </div>

              <div className="mb-3">
                <label htmlFor="roomPrice" className="form-label">
                  Room Price
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="roomPrice"
                  name="roomPrice"
                  value={newRoom.roomPrice}
                  onChange={handleRoomInputChange}
                  placeholder="Enter Room Price"
                />
              </div>

              <div className="mb-3">
                <label htmlFor="roomPhoto" className="form-label">
                  Room Photo
                </label>
                <input
                  type="file"
                  className="form-control"
                  id="roomPhoto"
                  name="roomPhoto"
                  onChange={handleImageChange}
                />
                {imagePreview && (
                  <img
                    src={imagePreview}
                    alt="Preview Room Image"
                    style={{ maxWidth: "400px", maxHeight: "400px" }}
                    className="img-fluid mt-2"
                  />
                )}
              </div>

              <div className="d-grid d-md-flex mt-2">
                <button className="btn btn-primary ml-5" type="submit">
                  Add Room
                </button>
              </div>
            </form>
          </div>
        </div>
      </section>
    </>
  );
};

export default AddRoom;
