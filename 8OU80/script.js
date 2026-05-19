const selector = document.getElementById("selector");
const attemptText = document.getElementById("attempt");
const sumText = document.getElementById("sum");
const resultText = document.getElementById("result");

let interval;

let currentNumber = 3;

let attempt = 1;

let total = 0;

let gameOver = false;

let mode8 = false;

let mode80 = false;

let isRolling = false;

function startLoop() {

  clearInterval(interval);

  resultText.innerHTML = "";

  interval = setInterval(() => {

    let range = [];

    // RANGE NORMAL
    for (let i = 3; i <= 26; i++) {
      range.push(i);
    }

    // EVENTOS
    if (mode8) {
      range.push(2);
    }

    if (mode80) {
      range.push(28);
    }

    currentNumber =
      range[Math.floor(Math.random() * range.length)];

    selector.textContent = currentNumber;

  }, 80);

  isRolling = true;
}

async function stopLoop() {

  // BLOQUEIOS
  if (!isRolling) return;

  if (gameOver) return;

  // PARA O LOOP
  isRolling = false;

  clearInterval(interval);

  // SOMA
  total += currentNumber;

  sumText.textContent = total;

  // =================================
  // EVENTOS ESPECIAIS
  // =================================

  if (attempt === 2) {

    // MODO 8
    if (total === 6) {

      mode8 = true;

      activateMode8();

      attempt++;

      attemptText.textContent = attempt;

      setTimeout(() => {

        startLoop();

      }, 1500);

      return;
    }

    // MODO 80
    if (total === 52) {

      mode80 = true;

      activateMode80();

      attempt++;

      attemptText.textContent = attempt;

      setTimeout(() => {

        startLoop();

      }, 1500);

      return;
    }
  }

  // =================================
  // SE FOI A TERCEIRA JOGADA
  // =================================

  if (attempt >= 3) {

    await endGame();

    return;
  }

  // =================================
  // PRÓXIMA RODADA NORMAL
  // =================================

  attempt++;

  attemptText.textContent = attempt;

  setTimeout(() => {

    startLoop();

  }, 700);
}

async function endGame() {

    gameOver = true;

    clearInterval(interval);

    resultText.innerHTML =
        `🏆 SCORE ${total}`;

    await saveScore(total);

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

  gameOver = false;

  mode8 = false;

  mode80 = false;

  isRolling = false;

  selector.classList.remove("mode8");
  selector.classList.remove("mode80");

  selector.textContent = "3";

  attemptText.textContent = "1";

  sumText.textContent = "0";

  resultText.innerHTML = "";

  startLoop();
}

function activateMode8() {
    mode8 = true;

    resultText.innerHTML =
        "✨ MODO 8 LIBERADO ✨";

    selector.classList.add("mode8");

    shakeScreen();

    //playSpecialSound();
}

function activateMode80() {
    mode80 = true;

    resultText.innerHTML =
        "🔥 MODO 80 LIBERADO 🔥";

    selector.classList.add("mode80");

    shakeScreen();

    //playSpecialSound();
}

function shakeScreen() {

    document.body.classList.add("shake");

    setTimeout(() => {

        document.body.classList.remove("shake");

    }, 400);
}

document.addEventListener("keydown", (e) => {

  if (
    e.code === "Space" ||
    e.code === "Enter"
  ) {

    e.preventDefault();

    stopLoop();
  }
});

selector.addEventListener("pointerdown", () => {

  stopLoop();

});

renderScores();
startLoop();