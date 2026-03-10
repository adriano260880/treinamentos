const params = new URLSearchParams(window.location.search);
const isPublicView = params.get("view") === "public";

let state = {
  A: { points: 0, adv: 0, pen: 0 },
  B: { points: 0, adv: 0, pen: 0 },
  time: 0,
  interval: null
};

function syncLocal() {
  if (isPublicView) return;
  localStorage.setItem("matchState", JSON.stringify({
    A: state.A,
    B: state.B,
    time: state.time,
    nameA: nameA.innerText,
    nameB: nameB.innerText
  }));
}

function updateUI() {
  scoreA.innerText = state.A.points;
  scoreB.innerText = state.B.points;
  advA.innerText = state.A.adv;
  advB.innerText = state.B.adv;
  penA.innerText = state.A.pen;
  penB.innerText = state.B.pen;

  let minutes = Math.floor(state.time / 60);
  let seconds = state.time % 60;
  timer.innerText =
    String(minutes).padStart(2,'0') + ":" +
    String(seconds).padStart(2,'0');

  syncLocal();
}

window.addEventListener("storage", (event) => {
  if (event.key === "matchState" && isPublicView) {
    const data = JSON.parse(event.newValue);
    state.A = data.A;
    state.B = data.B;
    state.time = data.time;
    nameA.innerText = data.nameA;
    nameB.innerText = data.nameB;
    updateUI();
  }
});

function addPoints(p,v){ state[p].points+=v; updateUI(); }
function subtractPoints(p,v){ if(state[p].points>=v){ state[p].points-=v; updateUI(); }}
function addAdv(p){ state[p].adv++; updateUI(); }
function subAdv(p){ if(state[p].adv>0){ state[p].adv--; updateUI(); }}
function addPen(p){ state[p].pen++; updateUI(); }
function subPen(p){ if(state[p].pen>0){ state[p].pen--; updateUI(); }}

function startTimer(){
  if(state.interval) return;
  state.interval=setInterval(()=>{
    if(state.time>0){ 
        state.time--; 
        updateUI();
    } else {
        clearInterval(state.interval);
        generateMatchLog();
    }
  },1000);
}

function pauseTimer(){ 
    clearInterval(state.interval); state.interval=null;
    
}

function confirmReset() {
    const confirmed = confirm("Deseja realmente reiniciar o jogo?");

    if (confirmed) {
        resetMatch();
    }
}

function resetMatch(){

   generateMatchLog();
   window.location.reload();

}

function createMatch(){
  nameA.innerText = setupNameA.value + " " + setupRepresentaA.value || "Atleta A";
  nameB.innerText = setupNameB.value + " " + setupRepresentaB.value || "Atleta B";
  state.time = parseInt(setupTime.value)*60 || 300;

  setupScreen.style.display="none";
  scoreboard.style.display="block";
  updateUI();
}

if(isPublicView){
  document.querySelectorAll(".controls,#refControls,button").forEach(el=>el.style.display="none");
  document.body.style.cursor="none";

  const saved=localStorage.getItem("matchState");
  if(saved){
    const data=JSON.parse(saved);
    state.A=data.A;
    state.B=data.B;
    state.time=data.time;
    nameA.innerText=data.nameA;
    nameB.innerText=data.nameB;
    setupScreen.style.display="none";
    scoreboard.style.display="block";
    updateUI();
  }
}

function generateMatchLog() {
  const winner =
    state.A.points > state.B.points ? nameA.innerText :
    state.B.points > state.A.points ? nameB.innerText :
    "Draw";

  const now = new Date().toLocaleTimeString("pt-BR");

  const csvContent =
    "Hora; Nome; Pontos; Vantagem; Punição; Nome; Pontos; Vantagem; Punição\n" +
    `${now};${nameA.innerText};${state.A.points};${state.A.adv};${state.A.pen};` +
    `${nameB.innerText};${state.B.points};${state.B.adv};${state.B.pen}`;

  const blob = new Blob([csvContent], { type: "text/csv" });
  const url = URL.createObjectURL(blob);

  const a = document.createElement("a");
  a.href = url;
  a.download = now+nameA.innerText+nameB.innerText+".csv";
  a.click();

  URL.revokeObjectURL(url);
}