import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'timeAgo'
})
export class TimeAgoPipe implements PipeTransform {
    transform(value: string): string {
        const now = new Date();
        const timestamp = new Date(value);
        const secondsAgo = Math.floor((now.getTime() - timestamp.getTime()) / 1000);

        if (secondsAgo < 60) {
            return `${secondsAgo} seconds ago`;
        } else if (secondsAgo < 3600) {
            const minutesAgo = Math.floor(secondsAgo / 60);
            return `${minutesAgo} minutes ago`;
        } else if (secondsAgo < 86400) {
            const hoursAgo = Math.floor(secondsAgo / 3600);
            return `${hoursAgo} hours ago`;
        } else {
            const daysAgo = Math.floor(secondsAgo / 86400);
            return `${daysAgo} days ago`;
        }
    }
}
