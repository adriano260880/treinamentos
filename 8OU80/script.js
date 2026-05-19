const selector = document.getElementById("selector");
const attemptText = document.getElementById("attempt");
const sumText = document.getElementById("sum");
const resultText = document.getElementById("result");

let currentNumber = 3;
let interval;

let attempt = 1;
let total = 0;
let selectedNumbers = [];

let min = 3;
let max = 26;

function startLoop() {
    interval = setInterval(() => {

        let range = [];

        for (let i = min; i <= max; i++) {
            range.push(i);
        }

        // REGRA ESPECIAL DO 8
        if (
            attempt === 3 &&
            total === 6
        ) {
            range.push(2);
        }

        // REGRA ESPECIAL DO 80
        if (
            attempt === 3 &&
            total === 52
        ) {
            range.push(28)
        }

        currentNumber =
            range[Math.floor(Math.random() * range.length)];

        selector.textContent = currentNumber;

    }, 80);
}

function stopLoop() {

    clearInterval(interval);

    selectedNumbers.push(currentNumber);

    total += currentNumber;

    sumText.textContent = total;

    if (attempt >= 3) {
        endGame();
        return;
    }

    attempt++;

    attemptText.textContent = attempt;

    setTimeout(() => {
        startLoop();
    }, 700);
}

function endGame() {

    resultText.innerHTML =
            `🏆 SCORE FINAL: ${total}`;

    saveScore(total);

    // Reinicia automaticamente após 1 segundo
    setTimeout(() => {
        resetGame();
    }, 2000);
}

function saveScore(score) {

  let high =
    JSON.parse(localStorage.getItem("high")) || [];

  let low =
    JSON.parse(localStorage.getItem("low")) || [];

  const qualifiesHigh =
    high.length < 10 ||
    score > high[high.length - 1]?.score;

  const qualifiesLow =
    low.length < 10 ||
    score < low[low.length - 1]?.score;

  // NÃO entrou em nenhum ranking
  if (!qualifiesHigh && !qualifiesLow) {
    renderScores();
    return;
  }

  let initials =
    prompt("NOVO RECORDE! Digite 3 letras:");

  if (!initials) return;

  initials = initials
    .toUpperCase()
    .substring(0, 3);

  // TOP MAIORES
  if (qualifiesHigh) {

    high.push({ initials, score });

    high.sort((a, b) => b.score - a.score);

    high = high.slice(0, 10);

    localStorage.setItem(
      "high",
      JSON.stringify(high)
    );
  }

  // TOP MENORES
  if (qualifiesLow) {

    low.push({ initials, score });

    low.sort((a, b) => a.score - b.score);

    low = low.slice(0, 10);

    localStorage.setItem(
      "low",
      JSON.stringify(low)
    );
  }

  renderScores();
}

function renderScores() {

    const highUl =
        document.getElementById("highScores");

    const lowUl =
        document.getElementById("lowScores");

    let high =
        JSON.parse(localStorage.getItem("high")) || [];

    let low =
        JSON.parse(localStorage.getItem("low")) || [];

    highUl.innerHTML = "";
    lowUl.innerHTML = "";

    high.forEach(item => {
        highUl.innerHTML +=
            `<li>${item.initials}: ${item.score}</li>`;
    });

    low.forEach(item => {
        lowUl.innerHTML +=
            `<li>${item.initials}: ${item.score}</li>`;
    });
}

function resetGame() {
    clearInterval(interval);

    currentNumber = 3;

    attempt = 1;
    total = 0;

    selectedNumbers = [];

    selector.textContent = "3";

    attemptText.textContent = "1";

    sumText.textContent = "0";

    resultText.innerHTML = "";

    startLoop();
}

document.addEventListener("keydown", (e) => {

    if (
        e.code === "Space" ||
        e.code === "Enter"
    ) {
        stopLoop();
    }
});

renderScores();
startLoop();