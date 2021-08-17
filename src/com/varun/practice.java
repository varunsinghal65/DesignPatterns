package com.varun;

class Driver {
    public static void main(String[] args) {

        DistanceServiceClient service = new KiloToMetresAdapter(new DistanceService());
        System.out.println(service.getDistanceInMetres());

    }

    private interface DistanceServiceClient {
        int getDistanceInMetres();
    }

    private static class DistanceService {
        public int getDistanceInKiloMetres() {
            return 10;
        }
    }

    private static class KiloToMetresAdapter implements DistanceServiceClient {

        private final DistanceService distanceService;

        KiloToMetresAdapter(DistanceService distanceService) {
            this.distanceService = distanceService;
        }

        @Override
        public int getDistanceInMetres() {
            return distanceService.getDistanceInKiloMetres()*1000;
        }
    }
}


