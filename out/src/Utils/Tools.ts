export const dateFormater = (javaDate: string) => {
	const times = javaDate.split('T')
	times[1] = times[1].substr(0, times[1].indexOf('.'))

	return times.join(' ')
}
