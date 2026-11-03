document.getElementById("contactForm").addEventListener("submit", function (e) {
  const name = document.getElementById("name").value.trim();
  const email = document.getElementById("email").value.trim();
  const msg = document.getElementById("message").value.trim();
  let valid = true;

  document.getElementById("nameErr").textContent = "";
  document.getElementById("emailErr").textContent = "";
  document.getElementById("msgErr").textContent = "";

  if (name.length < 2) {
    document.getElementById("nameErr").textContent = "Name must be at least 2 characters";
    valid = false;
  }
  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    document.getElementById("emailErr").textContent = "Enter a valid email";
    valid = false;
  }
  if (msg.length < 10) {
    document.getElementById("msgErr").textContent = "Message must be at least 10 characters";
    valid = false;
  }
  if (!valid) e.preventDefault();
});