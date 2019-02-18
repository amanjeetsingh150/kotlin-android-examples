for dir in ./*;
do
	echo “”
	echo ">>>> Updating directory : " $dir
	(cd "$dir" && ./gradlew clean && ./gradlew);
	echo “”
	echo ">>>> Done."
done

echo ”Deleting build folders”
echo “”

./delete_build_dirs.sh

