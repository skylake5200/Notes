…or create a new repository on the command line
echo "# Notes" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin git@github.com:skylake5200/Notes.git
git push -u origin master


…or push an existing repository from the command line
git remote add origin git@github.com:skylake5200/Notes.git
git push -u origin master
