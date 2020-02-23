from rest_framework.pagination import PageNumberPagination
from rest_framework import viewsets
from rest_framework import mixins
from rest_framework.permissions import IsAuthenticated
from .models import Admindistrict, Electroaldistrict, Polingdivision, Gramaniladaridivision, Locations,Media_items
from .serializers import AdmindistrictSerializer, ElectroaldistrictSerializer, PolingdivisionSerializer, GramaniladaridivisionSerializer, LocationsSerializer, Media_itemsSerializer
from rest_framework.parsers import FileUploadParser
# Create your views here.

class AdmindistrictViewset(mixins.CreateModelMixin,mixins.ListModelMixin,mixins.UpdateModelMixin,mixins.RetrieveModelMixin,viewsets.GenericViewSet):
    queryset = Admindistrict.objects.all()
    serializer_class = AdmindistrictSerializer
    pagination_class = PageNumberPagination
    permission_classes = (IsAuthenticated,)
class ElectoraldistrictViewset(mixins.CreateModelMixin,mixins.ListModelMixin,mixins.UpdateModelMixin,mixins.RetrieveModelMixin,viewsets.GenericViewSet):
    queryset = Electroaldistrict.objects.all()
    serializer_class = ElectroaldistrictSerializer
    pagination_class = PageNumberPagination
    permission_classes = (IsAuthenticated,)

class PolingdivisionViewset(mixins.CreateModelMixin,mixins.ListModelMixin,mixins.UpdateModelMixin,mixins.RetrieveModelMixin,viewsets.GenericViewSet):
    permission_classes = [IsAuthenticated,]
    queryset = Polingdivision.objects.all()
    serializer_class = PolingdivisionSerializer
    pagination_class = PageNumberPagination
    
class LocationViewset(mixins.CreateModelMixin,mixins.ListModelMixin,mixins.UpdateModelMixin,mixins.RetrieveModelMixin,viewsets.GenericViewSet):   
    queryset = Locations.objects.all()
    serializer_class = LocationsSerializer
    permission_classes = (IsAuthenticated,)
    pagination_class = PageNumberPagination
class GramaniladariViewset(mixins.CreateModelMixin,mixins.ListModelMixin,mixins.UpdateModelMixin,mixins.RetrieveModelMixin,viewsets.GenericViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = Gramaniladaridivision.objects.all()
    serializer_class = GramaniladaridivisionSerializer
    pagination_class = PageNumberPagination
class MideaItemViewset(mixins.CreateModelMixin,mixins.ListModelMixin,mixins.RetrieveModelMixin,viewsets.GenericViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = Media_items.objects.all()
    parser_class = (FileUploadParser,)
    serializer_class = Media_itemsSerializer
    pagination_class = PageNumberPagination


