import axios from "axios";

const handleSubmit = (formData, setStatusDate, setStatusCvv, setStatusCardNumber, setStatusLuhn) => {
    const formDate = {
        date: formData.date
    };

    const formCVV = {
        cvv: formData.cvv,
        cardNumber: formData.cardNumber
    };

    const formCardNumber = {
        cardNumber: formData.cardNumber
    };

    axios.post('http://localhost:8080/dateSubmit', formDate)
        .then((response) => {
            console.log(response.data);
            // Handle the response from the backend
            setStatusDate(response.data);
        })
        .catch((error) => {
            console.error('Error submitting form:', error);
            // Handle the error
        });

    axios.post('http://localhost:8080/cvvSubmit', formCVV)
        .then((response) => {
            console.log(response.data);
            // Handle the response from the backend
            setStatusCvv(response.data);
        })
        .catch((error) => {
            console.error('Error submitting form:', error);
            // Handle the error
        });

    axios.post('http://localhost:8080/cardNumberSubmit', formCardNumber)
        .then((response) => {
            console.log(response.data);
            // Handle the response from the backend
            setStatusCardNumber(response.data);
        })
        .catch((error) => {
            console.error('Error submitting form:', error);
            // Handle the error
        });

    axios.post('http://localhost:8080/luhnSubmit', formCardNumber)
        .then((response) => {
            console.log(response.data);
            // Handle the response from the backend
            setStatusLuhn(response.data);
        })
        .catch((error) => {
            console.error('Error submitting form:', error);
            // Handle the error
        });
};

export default handleSubmit;