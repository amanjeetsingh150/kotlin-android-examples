echo
for dir in ./*;
do
	echo “”
	echo ">>>> Deleting build directory inside " $dir
	trash $dir/build/
	echo “”
	echo ">>>> Done."

done
