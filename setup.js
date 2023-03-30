const fs = require("fs");
const readline = require("readline");
const { execSync, exec } = require("child_process");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const nodePath = "src/main/app/node_modules";
const staticFrontBuildPath = "src/main/resources/static/";
const staticBackBuildPath = "./target/";

const resetColor = "\x1b[0m";
const redColor = "\x1b[31m";
const bgRed = "\x1b[41m";
const greenColor = "\x1b[32m"
const bgGreen = "\x1b[42m"
const bgWhite = "\x1b[47m"

const ErrorMsg = (msg="") => {
    console.log(bgRed+"|"+" ERROR "+"|"+resetColor+" "+redColor+msg+resetColor);
}

const SuccesMsg = (msg="") => {
    console.log(bgGreen+"|"+" DONE "+"|"+resetColor+" "+greenColor+msg+resetColor);
}

const log = (msg="") => {
    console.log(bgWhite+"|"+" INFO "+"|"+resetColor+" "+msg);
}



getFromGit();
setUp();

rl.question("Do you want to build the project and generate a WAR file (Y/N): ", function(res) {
    const inp = res.toLowerCase();
    if (inp!="y" && inp!="yes") process.exit();
    rl.close();
});

rl.on("close", function() {
  removeProd();
  generateProd();
});



function getFromGit() {
    log("Pulling from git...");
    try {
        execSync("git pull");
    } catch {
        stopWithError("An error occurred when updating project.")
    }
    log("Project updated!");
}


function setUp() {
  log("Installing node packages (please be patient)...");
  execSync("cd src/main/app && npm install --no-progress --loglevel=error");
  if (fs.existsSync(nodePath)) {
    log("Installation complete!");
  } else {
    stopWithError("Unable to install node modules")
  }
  SuccesMsg("Setup complete!")
}


function removeProd() {
  log("Removing local build...");
  deleteIfExist(staticFrontBuildPath);
  deleteIfExist(staticBackBuildPath);
}


function generateProd() {
  log("Building frontend...");
  try {
      execSync("cd src/main/app && npm run build");
  } catch {
      stopWithError("An error occurred when building frontend.")
  }
  log("Building backend...");
  try {
      execSync("mvn package");
  } catch {
      stopWithError("An error occurred when building backend. Run 'mvn package' for more details.")
  }
  SuccesMsg("Project successfully build, WAR file is located inside the /target folder");
}


function deleteIfExist(path="") {
  try {
      if (fs.existsSync(path)) {
          fs.rmSync(path, { recursive: true, force: true });
      }
  } catch {
      ErrorMsg("An error occurred when trying to delete "+path)
  }
}


function stopWithError(msg="") {
  ErrorMsg(msg);
  process.exit();
}