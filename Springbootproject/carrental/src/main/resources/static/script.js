const API_URL = "http://localhost:8080/api/cars";

document.getElementById("carForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const car = {
        name: name.value,
        brand: brand.value,
        pricePerDay: price.value,
        available: true
    };

    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(car)
    });

    loadCars();
});

async function loadCars() {
    const res = await fetch(API_URL);
    const cars = await res.json();

    carList.innerHTML = "";
    cars.forEach(car => {
        carList.innerHTML += `
            <li>
                <strong>${car.name}</strong> (${car.brand})  
                - ₹${car.pricePerDay}/day
            </li>
        `;
    });
}

loadCars();
