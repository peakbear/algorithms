import imageio
import os
import sys
import numpy as np

def detect_circular_smudge(tif_file: str):
	# configurations
	print('processing image: ', tif_file)
	raw_image = imageio.imread(tif_file)
	# modify sensitive value to adjust brightness detection
	sensitive = 0.995
	rows, cols = raw_image.shape
	os.makedirs('output/', exist_ok=True)
	
	# increase contrast
	min = raw_image.min()
	max = raw_image.max()
	delta = max - min
	# use sqrt curve and re-normalise to increase contrast
	preprocessing_image = pow((raw_image - min) / delta, 2) * max
	preprocessing_image = preprocessing_image.astype('uint16')
	preprocessing_image = np.where(preprocessing_image < np.quantile(preprocessing_image, sensitive), min, max)
	imageio.imwrite('output/1_preprocessing_image.tif', preprocessing_image)
	
	# detect edge
	# if a pixel is not on a edge, mark it as background
	image_edge = preprocessing_image.copy()
	for col in range(1, cols - 1):
	    for row in range(1, rows - 1):
	        if preprocessing_image[row, col-1] != min and preprocessing_image[row, col+1] != min and preprocessing_image[row-1, col] != min and preprocessing_image[row+1, col] != min:
	            image_edge[row, col] = min
	imageio.imwrite('output/2_image_edge.tif', image_edge)
	
	# detect circle
	# got the idea from tensorflow, to detect shape using unit detector
	# TODO, boundry conditions needs to be improved
	detector = np.array([
	    [0, 0, 1, 1, 0, 0],
	    [0, 1, 0, 0, 1, 0],
	    [1, 0, 0, 0, 0, 1],
	    [1, 0, 0, 0, 0, 1],
	    [0, 1, 0, 0, 1, 0],
	    [0, 0, 1, 1, 0, 0]])
	
	detected_image = np.zeros((rows, cols))
	for col in range(cols - 5):
	    for row in range(rows - 5):
	        detected_image[row][col] = np.sum(image_edge[row:row+6, col:col+6] * detector) / 12
	detected_image = detected_image.astype('uint16')
	centriod = np.where(detected_image > max * 0.6, max, min)
	imageio.imwrite('output/3_detected_centriod.tif', centriod)
	
	# draw a rectangle around detected circle
	# TODO, boundry conditions needs to be improved
	# TODO, might use color map to represent results
	labeled_image = raw_image.copy()
	for col in range(3, cols - 3):
	    for row in range(3, rows - 3):
	        if centriod[row][col] == max:
	            for i in range(0, 7):
	                labeled_image[row][col+i] = max
	                labeled_image[row+6][col+i] = max
	                labeled_image[row+i][col] = max
	                labeled_image[row+i][col+6] = max
	imageio.imwrite('output/4_labeled_image.tif', labeled_image)	

def main():
	detect_circular_smudge(sys.argv[1])

if __name__ == "__main__":
	main()